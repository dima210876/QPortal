package my.project.QPortal.repository;

import my.project.QPortal.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Pair;

public interface ResponseRepository extends JpaRepository<Response, Pair<Integer, Integer>> { }
