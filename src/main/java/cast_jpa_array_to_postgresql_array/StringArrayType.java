package cast_jpa_array_to_postgresql_array;

import com.vladmihalcea.hibernate.type.util.Configuration;
import cast_jpa_array_to_postgresql_array.descriptor.StringArrayTypeDescriptor;

/**
 * Maps an {@code String[]} array on a PostgreSQL ARRAY type. Multidimensional arrays are supported as well, as explained in <a href="https://vladmihalcea.com/multidimensional-array-jpa-hibernate/">this article</a>.
 * <p>
 * For more details about how to use it, check out <a href="https://vladmihalcea.com/how-to-map-java-and-sql-arrays-with-jpa-and-hibernate/">this article</a> on <a href="https://vladmihalcea.com/">vladmihalcea.com</a>.
 *
 * @author Vlad Mihalcea
 */
public class StringArrayType extends AbstractArrayType<String[]> {

    public static final StringArrayType INSTANCE = new StringArrayType();

    public StringArrayType() {
        super(
            new StringArrayTypeDescriptor()
        );
    }

    public StringArrayType(Configuration configuration) {
        super(
            new StringArrayTypeDescriptor(),
            configuration
        );
    }

    public String getName() {
        return "string-array";
    }
}