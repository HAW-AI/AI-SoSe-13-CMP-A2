package haw.ai.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BestellKomponentenTest.class, KundenKomponentenTest.class,
		LagerKomponentenTest.class, LieferKomponentenTest.class,
		RechnungsKomponentenTest.class, Szenario.class })
public class AllTests {

}
