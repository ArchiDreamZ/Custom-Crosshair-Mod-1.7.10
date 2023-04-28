/*
 * Decompiled with CFR 0.148.
 */
package sparkless101.crosshairmod.crosshair.properties;

public abstract class Property<T> {
    private String name;
    private String alias;
    protected T type;

    public Property(String name, String alias, T t) {
        this.type = t;
        this.name = name;
        this.alias = alias;
    }

    public void setType(T t) {
        this.type = t;
    }

    public T getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getAlias() {
        return this.alias;
    }

    public abstract void setValue(String var1);

    public abstract String getStringValue();
}

