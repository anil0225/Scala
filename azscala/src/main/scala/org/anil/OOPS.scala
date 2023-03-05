package org.anil

object OOPS {
  def polyMorphism() = {
    println("Scala Supports polymorphism by types")
    var traveller:Traveller =  new IndianTraveller
    traveller.origin()
    traveller = new USTraveller
    traveller.origin()
    traveller = new Traveller {
      override def origin(): Unit = println("I am an anonymous UFO ")
    }
    traveller.origin()
  }

  def generic() : Unit = {
    println("Generics allow to define templated Objects for various types having similar behaviours")
    val travel = new Travel[Car](new Car)
    travel.StartTravel()
    val travel1 = new Travel[Train](new Train)
    travel1.StartTravel()
    val travel2 = new Travel[Flight](new Flight)
    travel2.StartTravel()
  }

  def variance() : Unit = {
    println("Variance is concept of boxing and unboxing using the parent object")

    // val travel : Travel[Vehicle] = new Travel[Car](new Car)
    // travel.StartTravel()
    println("Failed to compile below statement because of invariance")
    println("val travel : Travel[Vehicle] = new Travel[Car](new Car)")

    val coTravel : CoTravel[Vehicle] = new CoTravel[Car](new Car)
    coTravel.StartTravel()

    // Contra Variance
    // We derive specific types of Generic types in unboxing
    var genericDealer : Dealer[Deal] = new Dealer[Deal](new Deal())
    var carDealer : Dealer[CarDeal] =  new Dealer[CarDeal](new CarDeal)

    println("Statement `carDealer = genericDealer` fails if not set for contravarianc `-V`")
    carDealer.executeDeal()
    println("We can now assign Generic Type to specific type with enabling ContraVariance")
    carDealer = genericDealer
    carDealer.executeDeal()

  }

  def execute(): Unit = {
    polyMorphism()
    generic()
    variance()
  }
}

trait Traveller{
  def origin()
}

class IndianTraveller extends Traveller {
  override def origin(): Unit = {
    println("I am from India")
  }
}

class USTraveller extends Traveller {
  override def origin(): Unit =  println("I am from USA")
}

abstract class Vehicle{
  def start : Unit
}

class Car extends Vehicle{
  override def start: Unit = println("Riding on road with a car")
}

class Train extends Vehicle{
  override def start: Unit = println("Riding on Tracks in a train")
}

class Flight extends Vehicle{
  override def start: Unit = print("Taking a flight")
}

class Travel[V] (var vehicle : Vehicle){
  def StartTravel(): Unit ={
    vehicle.start
  }
}

class CoTravel[+V] (var vehicle : Vehicle){
  def StartTravel(): Unit ={
    vehicle.start
  }
}

class Deal{
  def GetDeal(): Unit = {
    println("This is a generic deals")
  }
}

class CarDeal extends Deal {
  override def GetDeal(): Unit = {
    println("This is a Specific Car deal")
  }
}

class Dealer[-D] (var deal:Deal){
  def executeDeal(): Unit = {
    deal.GetDeal()
  }
}

