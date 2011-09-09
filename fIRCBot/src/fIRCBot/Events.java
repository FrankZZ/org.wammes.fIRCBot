package fIRCBot;
/*
public class Events
{

}



//Declare the event. It must extend EventObject.
public class MyEvent extends EventObject {
 public MyEvent(Object source) {
     super(source);
 }
}

//Declare the listener class. It must extend EventListener.
//A class must implement this interface to get MyEvents.
public interface MyEventListener extends EventListener {
 public void myEventOccurred(MyEvent evt);
}

//Add the event registration and notification code to a class.
public class MyClass {
 // Create the listener list
 protected javax.swing.event.EventListenerList listenerList =
     new javax.swing.event.EventListenerList();

 // This methods allows classes to register for MyEvents
 public void addMyEventListener(MyEventListener listener) {
     listenerList.add(MyEventListener.class, listener);
 }

 // This methods allows classes to unregister for MyEvents
 public void removeMyEventListener(MyEventListener listener) {
     listenerList.remove(MyEventListener.class, listener);
 }

 // This private class is used to fire MyEvents
 void fireMyEvent(MyEvent evt) {
     Object[] listeners = listenerList.getListenerList();
     // Each listener occupies two elements - the first is the listener class
     // and the second is the listener instance
     for (int i=0; i<listeners.length; i+=2) {
         if (listeners[i]==MyEventListener.class) {
             ((MyEventListener)listeners[i+1]).myEventOccurred(evt);
         }
     }
 }
}


MyClass c = new MyClass();

//Register for MyEvents from c
c.addMyEventListener(new MyEventListener() {
 public void myEventOccurred(MyEvent evt) {
     // MyEvent was fired
 }
});*/