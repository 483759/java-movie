import domain.Movie;
import domain.MovieRepository;
import java.util.ArrayList;
import view.InputView;
import view.OutputView;
import domain.Book;

import java.util.List;

public class MovieApplication {
  public static List<Book> bookList = new ArrayList<>();

  public static int CalcultatePayment(){
    int result = 0;
    for(Book book : bookList){
      result += book.FinalPayment();
    }
    return result;
  }

  public static void ContinueGame(List<Movie> movies) {
    int movieId = InputView.inputMovieId();
    OutputView.printOneMovie(movies, movieId);

    int movieSequence = InputView.InputMovieSchedule(movieId);
    int bookNum = InputView.InputBookingNumber(movieId, movieSequence);
    bookList.add(new Book(movieId, movieSequence, bookNum));
  }

  public static int PayMovieReservation(){
    OutputView.PrintReservation(bookList);
    int usingPoint = InputView.InputUsingPoint();
    int finalPayment = CalcultatePayment();
    return finalPayment - usingPoint;
  }

  public static void main(String[] args) {
    List<Movie> movies = MovieRepository.getMovies();
    OutputView.printMovies(movies);
    do {
      ContinueGame(movies);
    }while(InputView.InputContinue());
    int finalMoney = PayMovieReservation();
    OutputView.PrintPayment(finalMoney);
    //OutputView.printOnemovieSequence(movies, movieId, movieSequence);
    // TODO 구현 진행
  }
}
