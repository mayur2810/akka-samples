package com.temp.akka

import akka.actor.Actor
import com.temp.akka.cc.Processed

class Sink extends Actor {

  override def preStart() {
    println("Starting sink")
    println(this.self.path)
  }

  def receive = {
    case pr: Processed => {
      println("stored "+ pr.processedMsg)
    }
  }

}