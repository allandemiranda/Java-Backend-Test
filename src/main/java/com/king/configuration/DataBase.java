package com.king.configuration;

import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.model.ScoreModel;
import com.king.model.UserModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The simpler Data Base.
 */
public final class DataBase {

  private static final List<ScoreModel> table = new ArrayList<>();

  private DataBase() {
    throw new IllegalStateException("DataBase class");
  }

  /**
   * Query to add a score
   *
   * @param userModel the user model
   * @param level     the level
   * @param points    the points
   * @throws BadRequestException          the bad request exception
   * @throws InternalServerErrorException the internal server error exception
   *
   * @apiNote userModel and level will be unique
   */
  public static void addScore(UserModel userModel, int level, int points) throws BadRequestException, InternalServerErrorException {
    try {
      var data = new ScoreModel(userModel, level, points);
      table.remove(data);
      table.add(data);
    } catch (InputMismatchException e) {
      throw new BadRequestException(e.getMessage());
    } catch (NullPointerException e) {
      throw new InternalServerErrorException(e.getMessage());
    }
  }

  /**
   * Query to get a data limited in 15 by level.
   *
   * @param level the level
   * @return the level data limited in 15 row
   */
  public static Map<Integer, Integer> getLevelData(int level) {
    return table.stream()
      .filter(scoreModel -> level == scoreModel.level())
      .sorted(Comparator.comparingInt(ScoreModel::score).reversed())
      .limit(15)
      .collect(Collectors.toMap(
        scoreModel -> scoreModel.userModel().id(),
        ScoreModel::score,
        (o, o2) -> o2,
        LinkedHashMap::new
      ));
  }

}
