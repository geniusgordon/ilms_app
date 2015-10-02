package it.sephiroth.android.library.picasso;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by gordon on 10/2/15.
 */
public class PicassoTools {
    public static void clearCache (Picasso p) {
        p.cache.clear();
    }
}
