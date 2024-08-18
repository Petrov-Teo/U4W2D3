package functional_interfaces;

@FunctionalInterface
public interface StringModifier {
    public String modify(String str);
    // public void bla bla(); <-- Un interfaccia si dice funzionale se possibile uno ed uno solo metodo astratto
}
