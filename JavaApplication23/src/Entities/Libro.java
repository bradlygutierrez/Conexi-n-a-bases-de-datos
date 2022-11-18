package Entities;


/**
 * @author bradl
 * @version 1.0
 * @created 16-Nov-2022 8:08:54 PM
 */
public class Libro {

	private String copyRight;
	private int editionNumber;
	private String isbn;
	private String title;
	private Autor m_Autor;

    public Libro(String copyRight, int editionNumber, String isbn, String title, Autor m_Autor) {
        this.copyRight = copyRight;
        this.editionNumber = editionNumber;
        this.isbn = isbn;
        this.title = title;
        this.m_Autor = m_Autor;
    }

	public Libro(){

	}

	public void finalize() throws Throwable {

	}

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Autor getM_Autor() {
        return m_Autor;
    }

    public void setM_Autor(Autor m_Autor) {
        this.m_Autor = m_Autor;
    }
        
        
}//end Libro