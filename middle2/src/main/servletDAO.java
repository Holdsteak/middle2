package main;

public interface servletDAO {
	public servletBean select_PK(int Seq);

	public servletBean create(servletBean bean);

	public servletBean update(servletBean bean);

	public boolean remove(int Seq);
}
