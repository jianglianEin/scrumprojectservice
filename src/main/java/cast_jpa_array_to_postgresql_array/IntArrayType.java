package cast_jpa_array_to_postgresql_array;

import com.vladmihalcea.hibernate.type.util.Configuration;
import cast_jpa_array_to_postgresql_array.descriptor.IntArrayTypeDescriptor;

/**
 * Maps an {@code int[]} array on a PostgreSQL ARRAY type. Multidimensional arrays are supported as well, as explained in <a href="https://vladmihalcea.com/multidimensional-array-jpa-hibernate/">this article</a>.
 * <p>
 * For more details about how to use it, check out <a href="https://vladmihalcea.com/how-to-map-java-and-sql-arrays-with-jpa-and-hibernate/">this article</a> on <a href="https://vladmihalcea.com/">vladmihalcea.com</a>.
 *
 * @author Vlad Mihalcea
 */
public class IntArrayType extends AbstractArrayType<int[]> {

    public static final IntArrayType INSTANCE = new IntArrayType();

    public IntArrayType() {
        super(
            new IntArrayTypeDescriptor()
        );
    }

    public IntArrayType(Configuration configuration) {
        super(
            new IntArrayTypeDescriptor(), configuration
        );
    }

    public String getName() {
        return "int-array";
    }
}