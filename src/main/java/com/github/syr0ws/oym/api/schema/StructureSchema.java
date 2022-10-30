package com.github.syr0ws.oym.api.schema;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents the internal and simplified structure of an object type.
 */
public class StructureSchema<T> {

    private final Class<T> type;
    private final Set<StructureField<?>> fields = new LinkedHashSet<>();

    public StructureSchema(@NotNull Class<T> type, @NotNull Set<StructureField<?>> fields) {
        this.type = type;
        this.fields.addAll(fields); // Avoid unwanted changes.
    }

    /**
     * Get all the fields which are annotated with @Key and which can be mapped.
     * @return an immutable set of fields.
     */
    public Set<StructureField<?>> getFields() {
        return Collections.unmodifiableSet(this.fields);
    }

    /**
     * The object type the structure represents.
     * @return an object type.
     */
    public Class<T> getStructureType() {
        return this.type;
    }
}
