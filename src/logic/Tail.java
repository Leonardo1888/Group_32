package logic;
// This enumeration is used for the Tails in the game: on the Board, Bookshelf, common goal and personal goal card and everywhere a Tail is used
// X = FILLABLE (IN BOOKSHELF), EMPTY, CAT, BOOK, GAME, FRAME, TROPHEY, PLANT, SAME/SAMPLE, DIFFERENT

public enum Tail {
	X,	// 'X' means a Tail which is Fillable in Bookshelf
	E,	// 'E' is empty. It means there are no Tails in that spot
	C,	// CAT
	B,	// BOOK
	G,	// GAME
	F,	// FRAME
	T,	// TROPHEY
	P,	// PLANT
	S,	// SAME/SAMPLE -> Used in CommonGoals
	D	// DIFFERENT -> used in CommonGoals
}