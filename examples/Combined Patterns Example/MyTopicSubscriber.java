package com.journaldev.design.observer;
 
public class MyTopicSubscriber implements Observer {
     
    private String name;
    private Subject topic;
     
    public MyTopicSubscriber(String nm){
        this.name=nm;
    }
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        String msg = (String) getTopic().getUpdate(this);
        if(msg == null){
            System.out.println(name+":: No new message");
        }else
        System.out.println(name+":: Consuming message::"+msg);
    }

    private Subject getTopic() { return topic; }
 
    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }
 
}