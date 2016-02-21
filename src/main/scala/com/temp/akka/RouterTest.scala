package com.temp.akka


import akka.actor.ActorSystem
import akka.actor.Props

object RouterTest {
  
  def main(args: Array[String]): Unit = {
    
    implicit val system = ActorSystem("LocalSystem")
    //init router
    val router = system.actorOf(Props[MyRouter], name = "myRouter")
    //init sink
    val sink = system.actorOf(Props[Sink], name = "sink1")
    
    //send messages
    for( i <- 1 to 5){
      router ! "test"+i
    }
    
    //stop router
    router ! "done"
    
    
  /*    for( i <- 5 to 10){
      router ! "test"+i
    }*/
    
    
  }
  
  
}