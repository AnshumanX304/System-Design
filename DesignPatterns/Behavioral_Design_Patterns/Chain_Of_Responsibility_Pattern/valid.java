package DesignPatterns.Behavioral_Design_Patterns.Chain_Of_Responsibility_Pattern;
abstract class Approver{
    Approver nextApprover;
    public void setNextApprover(Approver nextApprover){
        this.nextApprover=nextApprover;
    }
    public abstract void approveLeave(int days);
}


class TeamLead extends Approver{
    @Override
    public void approveLeave(int days){
        if(days<=2){
            System.out.println("The leave is approved by team lead for "+days+" days");
        }
        else if(nextApprover!=null){
            nextApprover.approveLeave(days);
        }
    }
}

class Manager extends Approver{
    @Override
    public void approveLeave(int days){
        if(days<=5){
            System.out.println("The leave is approved by manager for "+days+" days");
        }
        else if(nextApprover!=null){
            nextApprover.approveLeave(days);
        }
    }
}

class Director extends Approver{
    @Override
    public void approveLeave(int days){
        if(days<=10){
            System.out.println("The leave is approved by director for "+days+" days");
        }
        else if(nextApprover!=null){
            nextApprover.approveLeave(days);
        }
        else{
            System.out.println("Leave request denied. Too many days!");
        }
    }
}


public class valid {
    public static void main(String [] args){
        Approver teamlead=new TeamLead();
        Approver manager= new Manager();
        Approver director=new Director();

        teamlead.setNextApprover(manager);
        manager.setNextApprover(director);

        int leaveDays = 10;
        System.out.println("Employee requests " + leaveDays + " days of leave.");
        teamlead.approveLeave(leaveDays);
    }
    
}
