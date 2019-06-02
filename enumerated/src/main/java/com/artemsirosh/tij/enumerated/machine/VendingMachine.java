package com.artemsirosh.tij.enumerated.machine;

import java.util.Optional;

/**
 * Created on 07 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class VendingMachine {
    private ChangingStateContext context;

    VendingMachine() {
        this.context = new ChangingStateContext(State.RESTING, 0, null);
    }

    void next(Input input) {
        if (!this.getState().isTransient)
            this.context = this.getState().next(input, context);
    }

    void next() {
        if (this.getState().isTransient)
            this.context = this.getState().next(context);
    }

    State getState() {
        return this.context.getCurrentState();
    }

    int getAmount() {
        return this.context.getMoneyAmount();
    }

    enum State {
        RESTING {
            ChangingStateContext next(Input input, ChangingStateContext context) {
                final State nextState;
                final int nextAmount;
                switch(input.category()) {
                    case MONEY:
                        nextAmount = context.getMoneyAmount() + input.amount();
                        nextState = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        nextState = TERMINAL;
                        nextAmount = context.getMoneyAmount();
                        break;

                    default:
                        nextState = RESTING;
                        nextAmount = context.getMoneyAmount();
                }

                return new ChangingStateContext(nextState, nextAmount, context.getCurrentSelection().orElse(null));
            }
        },
        ADDING_MONEY {
            ChangingStateContext next(Input input, ChangingStateContext context) {

                final State nextState;
                final int nextAmount;
                final Input selection;
                switch(input.category()) {

                    case MONEY:
                        nextAmount = context.getMoneyAmount() + input.amount();
                        nextState = ADDING_MONEY;
                        selection = context.getCurrentSelection().orElse(null);
                        break;

                    case ITEM_SELECTION:
                        selection = input;
                        nextAmount = context.getMoneyAmount();
                        if(context.getMoneyAmount() < selection.amount()) {

                            System.out.println("Insufficient money for " + selection);
                            nextState = context.getCurrentState();
                        } else {
                            nextState = DISPENSING;
                        }
                        break;

                    case QUIT_TRANSACTION:
                        nextAmount = context.getMoneyAmount();
                        nextState = GIVING_CHANGE;
                        selection = context.getCurrentSelection().orElse(null);
                        break;

                    case SHUT_DOWN:
                        nextAmount = context.getMoneyAmount();
                        nextState = TERMINAL;
                        selection = context.getCurrentSelection().orElse(null);
                        break;

                    default:
                        nextAmount = context.getMoneyAmount();
                        nextState = context.getCurrentState();
                        selection = context.getCurrentSelection().orElse(null);

                }

                return new ChangingStateContext(nextState, nextAmount, selection);
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {
            ChangingStateContext next(ChangingStateContext context) {
                final Input selection = context.getCurrentSelection()
                        .orElseThrow(() -> new IllegalStateException("Should contain input."));

                System.out.println("here is your " + selection);
                final int amount = context.getMoneyAmount() - selection.amount();
                return new ChangingStateContext(GIVING_CHANGE, amount, selection);
            }
        },

        GIVING_CHANGE(StateDuration.TRANSIENT) {
            ChangingStateContext next(ChangingStateContext context) {

                if(context.getMoneyAmount() > 0) {
                    System.out.println("Your change: " + context.getMoneyAmount());
                }

                return new ChangingStateContext(RESTING, 0, context.getCurrentSelection().orElse(null));
            }
        },

        TERMINAL;

        private boolean isTransient = false;

        State() {}

        State(StateDuration trans) {
            isTransient = true;
        }

        ChangingStateContext next(Input input, ChangingStateContext context) {
            throw new RuntimeException("Only call " +
                    "next(Input input) for non-transient states");
        }

        ChangingStateContext next(ChangingStateContext context) {
            throw new RuntimeException("Only call next() for " +
                    "StateDuration.TRANSIENT states");
        }
    }

    enum StateDuration {
        TRANSIENT
    }

    private static class ChangingStateContext {
        private final State currentState;
        private final int moneyAmount;
        private final Input selection;

        ChangingStateContext(State currentState, int moneyAmount, Input selection) {
            this.currentState = currentState;
            this.moneyAmount = moneyAmount;
            this.selection = selection;
        }

        State getCurrentState() {
            return currentState;
        }

        int getMoneyAmount() {
            return moneyAmount;
        }

        Optional<Input> getCurrentSelection() {
            return Optional.ofNullable(selection);
        }
    }
}
