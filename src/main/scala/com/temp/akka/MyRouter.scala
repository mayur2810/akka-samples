package com.temp.akka

import akka.routing.{ ActorRefRoutee, RoundRobinRoutingLogic }
import akka.actor.Actor
import akka.routing.ActorRefRoutee
import akka.actor.Props
import akka.routing.Router
import akka.actor.Terminated

class MyRouter extends Actor {
  var router = {
    val routees = Vector.fill(5) {
      val r = context.actorOf(Props[Worker])
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(), routees)
    }

  def receive = {
    case "done" => {
      println("stopping..")
      context stop self
    }
    case w: String =>
      router.route(w, sender())
/*    case Terminated(a) =>
      router = router.removeRoutee(a)
      val r = context.actorOf(Props[Worker])
      context watch r
      router = router.addRoutee(r)*/

  }
}