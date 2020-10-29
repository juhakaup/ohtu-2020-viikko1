package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varastoEiTyhja;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoEiTyhja = new Varasto(10,2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoEiTyhjanVaraston() {
        assertEquals(2, varastoEiTyhja.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriSelviaaVaarastaSy�tteesta() {
        Varasto vaaraVarasto = new Varasto(0);
        Varasto vaaraVarastoEiTyhja = new Varasto(0, 2);
        Varasto vaaraVarastoEiTyhjaNegSaldo = new Varasto(0, -1);
        assertEquals(0, vaaraVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, vaaraVarastoEiTyhja.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, vaaraVarastoEiTyhjaNegSaldo.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void vaaraLisaysEiLisaaSaldoa() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tilaaSuurempiLisaysTayttaaVaraston() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void vaaraOttaminenPalauttaaNollan() {
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }
    
    @Test
    public void saldoaSuurempiOttaminenAntaaKaiken() {
        assertEquals(2, varastoEiTyhja.otaVarastosta(20), vertailuTarkkuus);
    }
    
    @Test
    public void toStringTulostaaOikein() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }

}