package com.brevitaz.config;

import javafx.scene.NodeBuilder;
import org.elasticsearch.common.settings.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.springframework.beans.factory.FactoryBean;

public class ESFactoryBean implements FactoryBean<Node>{

    private Node node;
    @Override
    public Node getObject() throws Exception {
        return getNode();
    }

    @Override
    public Class<?> getObjectType() {
        return Node.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private Node getNode() {

        return node;
    }
}
