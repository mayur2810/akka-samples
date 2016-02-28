package com.temp.akka

import akka.actor.Actor
import akka.actor.Props
import com.temp.akka.cc.`package`.Processed

class Worker extends Actor {

  //val sink = context.actorSelection("/user/sink1")
  
  //remote selection
  val sink = context.actorSelection("akka.tcp://MClusterSystem@127.0.0.1:2560/user/sink1")

  override def preStart() {
    println("Starting Worker")
  }

  def receive = {
    case str: String => {
      println(s"processing String $str")
      sink ! Processed(str)
    }
    case _ => println("received unknown message")
  }

}