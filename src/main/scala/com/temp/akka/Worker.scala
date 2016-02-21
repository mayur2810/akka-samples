package com.temp.akka

import akka.actor.Actor
import akka.actor.Props
import com.temp.akka.cc.`package`.Processed

class Worker extends Actor {
  
  val sink = context.actorSelection("/user/sink1")

  def receive = {
    case str:String => {
      println(s"processing $str")
      sink ! Processed(str)
      }
    case _ => println("received unknown message")
  }

}