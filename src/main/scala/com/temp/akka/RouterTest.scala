package com.temp.akka


import akka.actor.ActorSystem
import akka.actor.Props
import akka.cluster.routing.ClusterRouterPool
import akka.routing.RoundRobinPool
import akka.cluster.routing.ClusterRouterPoolSettings

object RouterTest {
  
  def main(args: Array[String]): Unit = {
    
    println("Started Router Test")
    implicit val system = ActorSystem("MClusterSystem")
    
    //init local router
    //val router = system.actorOf(Props[MyRouter], name = "myRouter")
    
    //init remote router
    val remoteRouterPool  = ClusterRouterPool(RoundRobinPool(4), ClusterRouterPoolSettings(4,2,true, None) )
    
    val router = system.actorOf(remoteRouterPool.props(Props[Worker])) 
    
    
    //init sink
    val sink = system.actorOf(Props[Sink], name = "sink1")
    
    //send messages
   Thread.sleep(1000)
    for( i <- 1 to 500){
      router ! "test"+i
    }
    
    //stop router
   // router ! "done"
    
    
  /*    for( i <- 5 to 10){
      router ! "test"+i
    }*/
    
    
  }
  
  
}