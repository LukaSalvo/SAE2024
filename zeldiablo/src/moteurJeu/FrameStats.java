package moteurJeu;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

/**
 * Classe pour les statistiques des frames

 */
public class FrameStats {
    /**
     * Attributs: frameCount, meanFrameInterval, text
     */
    private long frameCount;
    private double meanFrameInterval; // millis
    private final ReadOnlyStringWrapper text = new ReadOnlyStringWrapper(this, "text", "Frame count: 0 Average frame interval: N/A");

    /**
     * Guetteur du nombre de frames
     * @return
     */
    public long getFrameCount() {
        return frameCount;
    }

    /**
     * Guetteur de l'intervalle moyen entre les frames
     * @return
     */
    public double getMeanFrameInterval() {
        return meanFrameInterval;
    }

    /**
     * Methode pour ajouter une frame
     * @param frameDurationNanos
     */
    public void addFrame(long frameDurationNanos) {
        meanFrameInterval = (meanFrameInterval * frameCount + frameDurationNanos / 1_000_000.0) / (frameCount + 1);
        frameCount++;
        text.set(toString());
    }

    /**
     * Guetteur du texte
     * @return
     */
    public String getText() {
        return text.get();
    }

    /**
     * Guetteur de la propriété text
     * @return
     */
    public ReadOnlyStringProperty textProperty() {
        return text.getReadOnlyProperty();
    }

    /**
     * Methode pour afficher les statistiques des frames
     * @return
     */
    @Override
    public String toString() {
        return String.format("Frame count: %,d Average frame interval: %.3f milliseconds", getFrameCount(), getMeanFrameInterval());
    }
}