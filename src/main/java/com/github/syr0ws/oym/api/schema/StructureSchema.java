package com.github.syr0ws.oym.api.schema;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class StructureSchema<T> {

    private final Class<T> type;
    private final Set<StructureField<?>> fields = new LinkedHashSet<>();

    public StructureSchema(@NotNull Class<T> type, @NotNull Set<StructureField<?>> fields) {
        this.type = type;
        this.fields.addAll(fields); // Avoid unwanted changes.
    }

    public Set<StructureField<?>> getFields() {
        return Collections.unmodifiableSet(this.fields);
    }

    public Class<T> getStructureType() {
        return this.type;
    }
}
