package com.example.fragmenttest.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Fikret Xhemrushi","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut finibus ante, vel fermentum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec venenatis lobortis mattis. Sed tempor vestibulum tortor sed interdum. Sed luctus ligula non est rhoncus, vitae pretium justo pulvinar. Morbi ut orci a sapien varius pharetra. Cras quis consectetur elit, at mollis arcu. Donec in nisl ex"));
        addItem(new DummyItem("2", "Hatixhe Shtiklla","Nulla a massa auctor, molestie enim vel, dignissim magna. Maecenas ac dolor nec est condimentum feugiat. Fusce ac viverra augue. Duis pellentesque molestie ante, quis dapibus augue cursus condimentum. Maecenas mattis nunc ultrices gravida sollicitudin."));
        addItem(new DummyItem("3", "Rragip Struja","Aliquam varius ultricies tortor et viverra. Nullam porttitor porttitor tempus. Quisque ultricies lorem pulvinar massa venenatis interdum. In a iaculis magna. Nulla condimentum cursus eros. Nulla facilisi. Aenean ligula neque, finibus ac sapien ac, lacinia viverra mi. Vivamus sed gravida lacus. Vivamus nec purus in lorem vulputate rhoncus. "));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String title;
        public String content;

        public DummyItem(String id, String title, String content) {
            this.id = id;
            this.content = content;
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
