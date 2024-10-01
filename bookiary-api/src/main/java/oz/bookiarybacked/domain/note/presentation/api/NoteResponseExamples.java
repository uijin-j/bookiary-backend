package oz.bookiarybacked.domain.note.presentation.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NoteResponseExamples {

	public static final String NOTE_CREATE_201_RESPONSE = """	
		{
		    "code": 201,
		    "status": "CREATED",
		    "message": "CREATED",
		    "data": null
		}
		""";

	public static final String NOTE_UPDATE_200_RESPONSE = """
		{
		    "code": 200,
		    "status": "OK",
		    "message": "OK",
		    "data": null
		}
		""";

	public static final String NOTE_DELETE_204_RESPONSE = """
		{
		    "code": 204,
		    "status": "NO_CONTENT",
		    "message": "NO_CONTENT",
		    "data": null
		}
		""";
}
