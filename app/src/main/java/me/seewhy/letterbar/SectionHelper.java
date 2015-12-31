package me.seewhy.letterbar;

/**
 * Created by BG204119 on 2015/12/31.
 */
public interface SectionHelper {
    boolean isSectionHeaderPosition(int position);
    int sectionedPositionToPosition(int sectionedPosition);
    int positionToSectionedPosition(int position);
}
