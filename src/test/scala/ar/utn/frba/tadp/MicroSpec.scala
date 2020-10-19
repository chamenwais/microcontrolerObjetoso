package ar.utn.frba.tadp

import org.scalatest.{FreeSpec, Matchers}

class MicroSpec extends FreeSpec with Matchers{

  "suma simple" - {
    "debería dar 32 en el registro A" in {
      val runner= new Run
      runner.slots(0) = 10
      runner.slots(1) = 22
      val instrucciones = List(new LOAD(0), new SWAP, new LOAD(1), new ADD, new STORE(9))
      runner.run(instrucciones)
      runner.slots(9) should equal(32)
    }
  }
}
