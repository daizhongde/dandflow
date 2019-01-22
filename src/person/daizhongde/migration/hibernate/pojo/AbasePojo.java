package person.daizhongde.migration.hibernate.pojo;
/**
 * all pojo must extend this pojo class
 * method hashCode and equals are necessary, 
 * You can compare specific pojo by extend the two method in specific pojo
 * @author dzd
 *
 */
public abstract class AbasePojo {
	int i = 0;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AbasePojo other = (AbasePojo) obj;
		if (i != other.i)
			return false;
		return true;
	}
	
}
