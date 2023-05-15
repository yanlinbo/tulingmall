package com.ylb.mybatise.build;

import com.ylb.mybatise.session.Configuration;
import com.ylb.mybatise.type.TypeAliasRegistry;

public abstract class BaseBuilder {

    //configuration是父类的类属性
    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    /**
     *
     * @param configuration
     */
    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();  //还有这种写法？
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
