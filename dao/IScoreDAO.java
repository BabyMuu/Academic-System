package dao;

import entity.Score;

import java.util.List;

public interface IScoreDAO {


    List<Score> findAll(int nowPage, int pageSize);

    List<Score> findById(int id);

    public boolean insert(Score score);

    public boolean update(Score score);

    public boolean delete(String id);
}
