package ar.utn.frba.tadp

abstract class Microcontroler(var registroA: Int = 0,var registroB: Int = 0,var slots: Array[Int] = new Array[Int](128)){
  val REGISTRO_MAX_VALUE=1024
  def execute(load: LOAD)
  def execute(add: ADD)
  def execute(swap: SWAP)
  def execute(store: STORE)
}

class Run extends Microcontroler{
  override def execute(load: LOAD) = {
    registroA = slots(load.address)
    printCurrentStatus("LOAD")
  }

  override def execute(add: ADD) = {
    val suma=  registroA +  registroB
    if (suma > REGISTRO_MAX_VALUE) {
       registroB = REGISTRO_MAX_VALUE
       registroA = (suma - REGISTRO_MAX_VALUE)
    } else {
       registroA = suma
       registroB = 0
    }
    printCurrentStatus("ADD")
  }

  override def execute(swap: SWAP) = {
    val swapValue=registroB
    registroB=registroA
    registroA=swapValue
    printCurrentStatus("SWAP")
  }

  override def execute(store: STORE): Unit = {
    slots(store.address) = registroA
    printCurrentStatus("STORE")
  }

  def run(instrucciones: List[Instruccion])= {
    instrucciones.foreach{instruccion => instruccion.doExecute(this)}
  }

  private def printCurrentStatus(nombreOper: String) = {
    println("Ejecutando " + nombreOper)
    println(s"\tA:$registroA")
    println(s"\tB:$registroB")
  }

}

abstract class Instruccion {
  def doExecute(microcontroler: Microcontroler)
}

class LOAD(var address: Int) extends Instruccion {
  override def doExecute(microcontroler: Microcontroler): Unit = {
    microcontroler.execute(this)
  }
}

class ADD() extends Instruccion {
  override def doExecute(microcontroler: Microcontroler): Unit = {
    microcontroler.execute(this)
  }
}

class SWAP extends Instruccion {
  override def doExecute(microcontroler: Microcontroler): Unit = {
    microcontroler.execute(this)
  }
}

class STORE(var address: Int) extends Instruccion {
  override def doExecute(microcontroler: Microcontroler): Unit = {
    microcontroler.execute(this)
  }
}
