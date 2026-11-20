from abc import ABC,abstractmethod

class Approver(ABC):

    def __init__(self):
        self.next_approver=None
    def set_next(self,approver:Approver):
        self.next_approver=approver
    @abstractmethod
    def approve_leave(self,days:int):
        pass

class TeamLead(Approver):
    def approve_leave(self,days:int):
        if days<=2:
            print(f"Leaved approved by team lead for {days} days")
        elif self.next_approver is not None:
            self.next_approver.approve_leave(days)

class Manager(Approver):
    def approve_leave(self,days:int):
        if days<=5:
            print(f"Leaved approved by Manager for {days} days")
        elif self.next_approver is not None:
            self.next_approver.approve_leave(days)

class Director(Approver):
    def approve_leave(self,days:int):
        if days<=10:
            print(f"Leaved approved by Director for {days} days")
        else:
            print(f"To many leaves, request denied !!")

            

if __name__=="__main__":

    teamlead=TeamLead()
    manager=Manager()
    director=Director()

    teamlead.set_next(manager)
    manager.set_next(director)

    teamlead.approve_leave(5)

# Chain of Responsibility (CoR): Pass a request through a chain of handlers, where each handler either processes it or passes it to the next handler.



    
