package it.unibo.mvc.impl.view;

import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawNumberViewObserver;
import it.unibo.mvc.impl.DrawResult;

public class StandardOutputView implements DrawNumberView {

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
        System.out.println("You must enter a number"); // NOPMD: exercises require
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription()); // NOPMD: exercises require
    }

    @Override
    public void displayError(final String message) {
        System.out.println(message); // NOPMD: exercises require
    }
}
