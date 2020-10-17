package ar.utn.frba.tadp

import org.scalatest.{FreeSpec, Matchers}

class MicroSpec extends FreeSpec with Matchers{

  "suma simple" - {
    "debería dar 32 en el registro A" in {
      val micro= new MicrocontrolerImpl
      micro.slots(0) = 10
      micro.slots(1) = 22
      val instrucciones = List(new LOAD(0), new SWAP, new LOAD(1), new ADD)
      micro.run(instrucciones)
      micro.registroA should equal(32)
    }
  }
}
