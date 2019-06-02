class Dog:
    def speak(self):
        print('Arf!')

    def sit(self):
        print("Sitting")

    def reproduce(self):
        pass


class Robot:
    def speak(self):
        print("Click!")

    def sit(self):
        print("Clank!")

    def oil_change(self):
        pass


class Mime:
    def sit(self):
        print("Pretend to sit")

    def push_invisible_wall(self):
        pass


def perform(anything):
    anything.speak()
    anything.sit()


a = Dog()
b = Robot()
c = Mime()

perform(a)
perform(b)
# perform(c)
