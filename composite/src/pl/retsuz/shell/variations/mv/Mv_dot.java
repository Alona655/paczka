package pl.retsuz.shell.variations.mv;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Mv_dot extends CommandVariation {
    public Mv_dot(ICommandVariation next, ICommand parent) {
        super(next,parent,"([a-zA-Z0-9.l\\/_]*\\s\\.)");
    }

    @Override
    public void make(String params)
    {
        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        String[] src = params.split(" ");
        try{
            IComposite csrc = c.findElementByPath(src[0]);
            IComposite csrc2 = csrc.getParent();
            Composite.moveElement(csrc2, c, csrc);
            System.out.println("Plik został przeniesiony");
        }
        catch (Exception e)
        {
            System.out.println("Cos poszlo nie tak");
        }
    }

}
