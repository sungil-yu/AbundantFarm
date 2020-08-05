package com.farm.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.farm.web.entity.Review;
import com.farm.web.entity.ReviewNoticeVo;
import com.farm.web.entity.ReviewView;
import com.farm.web.entity.ReviewView2;
import com.farm.web.entity.DTO.ReviewViewofIndex;

@Mapper
public interface ReviewDao {

	@Select("SELECT R.* ,I.name as productName FROM ReviewView R join Item I on R.itemId = I.id where pub =1 and isdel =0 order by regDate desc limit 4")
	List<ReviewViewofIndex> indexOfReviews();
	
	@Select("select * from ReviewView2 where sellerId = ${id} and  ${field} like '%${query}%' order by regDate desc limit #{offset}, #{size}")
	List<ReviewView2> getList3(int id, int offset, int size, String field, String query);

	@Select("SELECT * FROM ReviewView order by regDate desc limit 10")
	List<ReviewView> getList2();

	@Select("select r.*, m.name from Review r join Member m on r.writerId = m.id where ${field} like '%${query}%' order by regDate desc limit #{offset}, #{size}")
	List<ReviewView> getList(int offset, int size, String field, String query);

	
	@Delete("delete from Review where id in (${id})")
	int delete(String id);

	@Select("SELECT * FROM ReviewView WHERE ID=#{id}")
	ReviewView get(int id);

	@Insert("INSERT INTO Review (title,content) VALUES(#{title},#{content}")
	int insert(Review review);

	@Update("")
	int update(Review review);

	@Delete("")
	int delet(Review review);

	@Select("SELECT * FROM ReviewView where itemId = ${prId} limit 10")
	List<ReviewNoticeVo> selectByItemId(int prId);

	@Select("SELECT * FROM Review where id = ${noticeId} and itemId=${prId}")
	Review selectByReviewIdAndPrId(int noticeId, int prId);

	@Select("SELECT R.* FROM Review R join Item I on R.itemId = I.id  where I.id = ${prId} limit 10")
	List<Review> selectAll(int prId);

	@Select("SELECT * FROM ReviewView where itemId = ${prId} limit 10 offset ${offset}")
	List<ReviewNoticeVo> plusTenReviewRow(int offset, int prId);

	@Insert("INSERT INTO Review (id,writerId,itemId,title,content,hit,rate,regDate,like) "
			+ "VALUE(NULL,${review.writerId},${review.itemId},#{review.title},#{review.content},#{review.hit},#{review.rate},NOW(),#{review.like})")
	int InsertReview(Review review);

}
