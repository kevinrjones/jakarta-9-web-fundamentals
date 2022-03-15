package com.knowledgespike.blog;

import java.util.ArrayList;
import java.util.List;

public class NewsItems {

    private final List<NewsItem> newsItems = new ArrayList<>();

    public NewsItems() {
        newsItems.add(new NewsItem("Creating Servlets","Lorem ipsum dolor sit amet, consectetur adipiscing" +
                " elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. A diam maecenas sed enim " +
                "ut sem viverra aliquet eget. Lacus suspendisse faucibus interdum posuere. Aenean vel elit " +
                "scelerisque mauris pellentesque. Eget velit aliquet sagittis id consectetur purus. Integer enim " +
                "neque volutpat ac tincidunt vitae. Arcu ac tortor dignissim convallis aenean et tortor at. Arcu non" +
                " odio euismod lacinia at quis. ",1));

        newsItems.add(new NewsItem("Servlet Context","Ut eu sem integer vitae. Pellentesque massa placerat" +
                " duis ultricies. Accumsan tortor posuere ac ut. Ornare arcu odio ut sem nulla pharetra diam. Nunc " +
                "pulvinar sapien et ligula ullamcorper malesuada proin libero nunc. Vestibulum mattis ullamcorper " +
                "velit sed ullamcorper morbi tincidunt ornare massa. Nam libero justo laoreet sit amet cursus sit " +
                "amet dictum. Orci dapibus ultrices in iaculis nunc sed augue. Risus commodo viverra maecenas " +
                "accumsan lacus. Nisl pretium fusce id velit ut tortor pretium viverra.",1));
        newsItems.add(new NewsItem("Expression Language","Ullamcorper malesuada proin libero nunc. Etiam " +
                "non quam lacus suspendisse faucibus interdum posuere lorem. Vulputate ut pharetra sit amet. Congue " +
                "quisque egestas diam in arcu cursus euismod. Donec massa sapien faucibus et molestie ac. Vulputate " +
                "sapien nec sagittis aliquam malesuada bibendum arcu vitae. Neque vitae tempus quam pellentesque nec" +
                " nam aliquam sem et. Tincidunt id aliquet risus feugiat in ante. Feugiat in fermentum posuere urna " +
                "nec tincidunt.",2));

        newsItems.add(new NewsItem("OIDC","Mauris pharetra et ultrices neque. Tincidunt nunc pulvinar " +
                "sapien et ligula ullamcorper malesuada proin libero. Pharetra diam sit amet nisl suscipit " +
                "adipiscing. Facilisi morbi tempus iaculis urna id volutpat lacus. Enim facilisis gravida neque " +
                "convallis. Porttitor rhoncus dolor purus non enim praesent elementum. Lobortis scelerisque fermentum" +
                " dui faucibus in.",3));
        newsItems.add(new NewsItem("OAuth","Egestas sed sed risus pretium quam vulputate. Gravida dictum " +
                "fusce ut placerat orci nulla pellentesque dignissim enim. Cras sed felis eget velit aliquet " +
                "sagittis id. Aenean sed adipiscing diam donec adipiscing. Aliquet nibh praesent tristique magna sit " +
                "amet. Eu mi bibendum neque egestas congue quisque egestas diam in. Et malesuada fames ac turpis " +
                "egestas maecenas pharetra convallis posuere. Sed augue lacus viverra vitae congue eu consequat ac. " +
                "Lectus proin nibh nisl condimentum id. Orci ac auctor augue mauris. Enim lobortis scelerisque " +
                "fermentum dui faucibus in ornare quam. Egestas tellus rutrum tellus pellentesque eu tincidunt. " +
                "Aliquet porttitor lacus luctus accumsan tortor posuere ac ut.",3));
    }

    public List<NewsItem> getItems() {
        return newsItems;
    }


}
