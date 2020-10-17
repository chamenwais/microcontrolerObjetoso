package ar.utn.frba.tadp

abstract class Microcontroler(var registroA: Short = 0,var registroB: Short = 0,var slots: Array[Short] = new Array[Short](128)){
  def execute(instruccion: LOAD)
  def execute(instruccion: ADD)
  def execute(instruccion: SWAP)
}

class MicrocontrolerImpl extends Microcontroler{
  override def execute(instruccion: LOAD) = {
    println("Ejecutando LOAD")
  }
  override def execute(instruccion: ADD) = {
    println("Ejecutando ADD")
  }
  override def execute(instruccion: SWAP) = {
    println("Ejecutando SWAP")
  }
  def run(instrucciones: List[Instruccion])= {
    instrucciones.foreach{instruccion => instruccion.doExecute(this)}
  }
}

abstract class Instruccion {
  def doExecute(microcontroler: Microcontroler)
}

class LOAD(_address: Int) extends Instruccion {
  override def doExecute(microcontroler: Microcontroler): Unit = {
    microcontroler.registroA = microcontroler.slots(_address)
    microcontroler.execute(this)
  }
}

class ADD extends Instruccion {
  override def doExecute(microcontroler: Microcontroler): Unit = {
    val suma= microcontroler.registroA + microcontroler.registroB
    if (suma > 1024) {
      microcontroler.registroB = 1024
      microcontroler.registroA = (suma - 1024).asInstanceOf[Short]
    } else {
      microcontroler.registroB = suma.asInstanceOf[Short]
      microcontroler.registroA = 0
    }
    microcontroler.execute(this)
  }
}

class SWAP extends Instruccion {
  override def doExecute(microcontroler: Microcontroler): Unit = {
    val swapValue=microcontroler.registroB
    microcontroler.registroB=microcontroler.registroA
    microcontroler.registroA=swapValue
    microcontroler.execute(this)
  }
}
