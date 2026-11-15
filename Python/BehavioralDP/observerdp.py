
from abc import ABC, abstractmethod


# =====================================================
# 1. OBSERVER INTERFACE
# =====================================================
# Defines a common contract for all subscribers.
# Every subscriber must implement the update() method.
#
# This allows the Subject to notify different types
# of subscribers without knowing their concrete classes.

class Subscriber(ABC):

    @abstractmethod
    def update(self):
        pass


# =====================================================
# 2. CONCRETE OBSERVERS
# =====================================================
# Each Concrete Observer implements update() differently.
# All of them follow the Subscriber interface.


class YoutubeSubscriber(Subscriber):

    def __init__(self, user):
        self.user = user

    def update(self):
        print(f"Sending YouTube notification to {self.user}")


class SMSSubscriber(Subscriber):

    def __init__(self, user):
        self.user = user

    def update(self):
        print(f"Sending SMS notification to {self.user}")


class EmailSubscriber(Subscriber):

    def __init__(self, user):
        self.user = user

    def update(self):
        print(f"Sending Email notification to {self.user}")


# =====================================================
# 3. SUBJECT INTERFACE
# =====================================================
# Defines the operations that a Subject must support.
#
# A Subject is responsible for:
# 1. Adding observers.
# 2. Removing observers.
# 3. Notifying all observers.
# 4. Triggering events.

class YoutubeChannel(ABC):

    @abstractmethod
    def add_subscriber(self, subscriber):
        pass

    @abstractmethod
    def remove_subscriber(self, subscriber):
        pass

    @abstractmethod
    def notify_subscribers(self):
        pass

    @abstractmethod
    def upload_video(self, title):
        pass


# =====================================================
# 4. CONCRETE SUBJECT
# =====================================================
# Implements the Subject interface.
#
# Maintains a list of all registered subscribers.
# Whenever a video is uploaded, it notifies everyone
# in this list.

class YoutubeChannelImpl(YoutubeChannel):

    def __init__(self):
        # Stores all observers using their common interface.
        # This allows different subscriber types to coexist.
        self._subscribers = []

    def add_subscriber(self, subscriber):
        # Register an observer with the Subject.
        self._subscribers.append(subscriber)

    def remove_subscriber(self, subscriber):
        # Unregister an observer from the Subject.
        if subscriber in self._subscribers:
            self._subscribers.remove(subscriber)

    def notify_subscribers(self):
        # Iterate over all registered observers.
        # Polymorphism ensures that the correct update()
        # implementation is executed for each subscriber.
        for subscriber in self._subscribers:
            subscriber.update()

    def upload_video(self, title):
        # Uploading a video is the event that triggers
        # notifications to all registered observers.
        print(f"Video has been published with title: {title}")

        self.notify_subscribers()


# =====================================================
# 5. CLIENT CODE
# =====================================================
# The client creates the Subject and Concrete Observers.
# It registers observers with the Subject.
# The client does not need to know how notifications
# are internally implemented.

if __name__ == "__main__":

    # Create the Subject.
    ytchannel = YoutubeChannelImpl()

    # Create different Concrete Observers.
    smssubscriber = SMSSubscriber("Monty")
    youtubesubscriber = YoutubeSubscriber("Shonty")
    emailsubscriber = EmailSubscriber("Billu")

    # Register observers with the Subject.
    ytchannel.add_subscriber(smssubscriber)
    ytchannel.add_subscriber(emailsubscriber)
    ytchannel.add_subscriber(youtubesubscriber)

    # Uploading a video triggers notification to all observers.
    ytchannel.upload_video("Believe Shonty, Monty and Billu !!")
