from abc import ABC,abstractmethod

class MessageSender(ABC):
    @abstractmethod
    def send_message(self,message):
        pass

class SMSSender(MessageSender):
    def send_message(self, message):
        return f"message sent via SMS {message}"

class EmailSender(MessageSender):
    def send_message(self, message):
        return f"message sent via Email {message}"


class MessageType(ABC):

    def __init__(self,message_sender:MessageSender):
        self.message_sender=message_sender

    @abstractmethod
    def notify(self,message):
        pass


class UrgentMessage(MessageType):

    def notify(self, message):
        return self.message_sender.send_message(f"Urgent {message}")

class ScheduledMessage(MessageType):
    
    def notify(self, message):
        return self.message_sender.send_message(f"Scheduled {message}")

class NormalMessage(MessageType):
    
    def notify(self, message):
        return self.message_sender.send_message(message)


if __name__=="__main__":
    email_sender = EmailSender()
    sms_sender = SMSSender()

    notification1 = NormalMessage(email_sender)
    print(notification1.notify("Hello!"))

    notification2 = UrgentMessage(sms_sender)
    print(notification2.notify("Server is down!"))

    notification2 = ScheduledMessage(sms_sender)
    print(notification2.notify("Payment due!"))


# Bridge DP: Separates what an object does from how it does it, connecting both through composition 
# so they can evolve independently.