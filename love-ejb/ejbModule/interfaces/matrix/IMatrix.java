package interfaces.matrix;

import javax.ejb.Remote;

@Remote
public interface IMatrix {
	public String debug(String debug);
}
