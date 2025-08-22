package DesignPatterns.Behavioral_Design_Patterns.Observer_Design_Pattern;
import java.util.ArrayList;
import java.util.List;

// Observer Pattern Example
// ------------------------
// In this example:
// - "Subscriber" is the Observer interface
// - "YoutubeChannel" is the Subject interface
// - "YoutubeChannelImpl" is the Concrete Subject
// - "YoutubeSubscriber", "SMSSubscriber", "EmailSubscriber" are Concrete Observers

// Observer interface: any subscriber must implement "update" method
interface Subscriber {
    public void update();
}

// Concrete Observer: YoutubeSubscriber
class YoutubeSubscriber implements Subscriber {
    String user;
    public YoutubeSubscriber(String user) {
        this.user = user;
    }
    @Override
    public void update() {
        System.out.println("Sending YouTube notification to " + user);
    }
}

// Concrete Observer: SMSSubscriber
class SMSSubscriber implements Subscriber {
    String user;
    public SMSSubscriber(String user) {
        this.user = user;
    }
    @Override
    public void update() {
        System.out.println("Sending SMS notification to " + user);
    }
}

// Concrete Observer: EmailSubscriber
class EmailSubscriber implements Subscriber {
    String user;
    public EmailSubscriber(String user) {
        this.user = user;
    }
    @Override
    public void update() {
        System.out.println("Sending Email notification to " + user);
    }
}

// Subject interface: defines methods to manage observers (subscribers)
interface YoutubeChannel {
    void addSubscriber(Subscriber subscriber);     // attach an observer
    void removeSubscriber(Subscriber subscriber);  // detach an observer
    void notifySubscribers();                      // notify all observers
    void uploadVideo(String title);                // trigger event
}

// Concrete Subject: maintains a list of subscribers and notifies them when video is uploaded
class YoutubeChannelImpl implements YoutubeChannel {
    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(); // call update method of each observer
        }
    }

    @Override
    public void uploadVideo(String title) {
        System.out.println("Video has been published with title: " + title);
        notifySubscribers(); // notify all observers about the new video
    }
}

// Client code: demonstrates how Observer Pattern works
public class valid {
    public static void main(String[] args) {

        // Creating a YouTube channel (the Subject)
        YoutubeChannel ytchannel = new YoutubeChannelImpl();

        // Creating different subscribers (Observers)
        Subscriber smssubscriber = new SMSSubscriber("Monty");
        Subscriber youtubesubscriber = new YoutubeSubscriber("Shonty");
        Subscriber emailsubscriber = new EmailSubscriber("Billu");

        // Attaching subscribers to the channel
        ytchannel.addSubscriber(smssubscriber);
        ytchannel.addSubscriber(emailsubscriber);
        ytchannel.addSubscriber(youtubesubscriber);

        // Uploading a video triggers notification to all subscribers
        ytchannel.uploadVideo("Believe Shonty, Monty and Billu !!");
    }
}
