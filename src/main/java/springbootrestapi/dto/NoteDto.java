package springbootrestapi.dto;

import java.io.Serializable;

public class NoteDto implements Serializable {

	private Long noteId;
	private String title;
	private String description;

	public NoteDto() {
		super();
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
