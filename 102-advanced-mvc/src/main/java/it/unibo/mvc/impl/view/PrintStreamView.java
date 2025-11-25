package it.unibo.mvc.impl.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawNumberViewObserver;
import it.unibo.mvc.impl.DrawResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This class implements a view that can write on any PrintStream.
 */
public final class PrintStreamView implements DrawNumberView {

    private final PrintStream out;

    /**
     * Builds a new PrintStreamView.
     *
     * @param stream the {@link PrintStream} where to write
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "The PrintStream is intentionally shared and this class is designed to write to external streams"
    )
    public PrintStreamView(final PrintStream stream) {
        out = stream;
    }

    /**
     * Builds a {@link PrintStreamView} that writes on file, given a path.
     * 
     * @param path a file path
     * @throws FileNotFoundException  file doesn't exist
     */
    public PrintStreamView(final String path) throws FileNotFoundException {
        out = new PrintStream(new FileOutputStream(new File(path)), true, StandardCharsets.UTF_8);
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        /*
         * This UI is output only.
         */
    }

    @Override
    public void start() {
        /*
         * PrintStreams are always ready.
         */
    }

    @Override
    public void numberIncorrect() {
        out.println("You must enter a number");
    }

    @Override
    public void result(final DrawResult res) {
        out.println(res.getDescription());
    }

    @Override
    public void displayError(final String message) {
        out.println(message);
    }
}
