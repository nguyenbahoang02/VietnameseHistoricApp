package resolve;

import java.util.List;

interface IResolveData<E> {
	List<E> getDataFromHTML();
	List<E> getDataFromFile();
	void writeDatatoFileJSON(List<E> data);
}
