package com.example.demo.domain.contacts;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ContactRepository {
	
	@Select("SELECT "
			+ "contacts.contacts_id,"
			+ "contacts.recorded_time,"
			+ "contacts.send_or_receive,"
			+ "contacts.title, "
			+ "contacts.content,"
			+ "contacts.cliant_id,"
			+ "COALESCE(cliants.sei, '---') AS sei,"
			+ "COALESCE(cliants.mei, '---') AS mei "
			+ "FROM contacts "
			+ "LEFT JOIN cliants "
			+ "ON contacts.cliant_id = cliants.id")
	List<ContactEntity> findAll();

	@Insert("INSERT into contacts (recorded_time, send_or_receive, title, content, cliant_id) values "
			+ "(#{send_and_receive_time}, #{send_or_receive}, #{title}, #{content}, #{cliant_id})")
	void create(String send_and_receive_time, boolean send_or_receive, String title, String content, long cliant_id);

	@Select("SELECT "
			+ "contacts_id,"
			+ "recorded_time,"
			+ "CASE "
			+ "  WHEN send_or_receive = TRUE THEN 'S' "
			+ "  WHEN send_or_receive = FALSE THEN 'R' "
			+ "  ELSE 'R' "
			+ "END as send_or_receive,"
			+ "title,"
			+ "content,"
			+ "cliant_id "
			+ " FROM contacts WHERE contacts_id = #{contactId}")
	ContactEntityForEdit findById(long contactId);

	@Update("UPDATE contacts "
			+ "SET recorded_time = #{send_and_receive_time}, send_or_receive = #{send_or_receive}, title = #{title}, "
			+ "content = #{content}, cliant_id = #{cliant_id} "
			+ "WHERE contacts_id = #{contact_id}")
	void updateById(long contact_id, String send_and_receive_time, boolean send_or_receive, String title, String content, long cliant_id);

	@Delete("DELETE from contacts where contacts_id = #{contact_id}")
	void deleteById(long contact_id);
	
	@Select("SELECT * from contacts WHERE cliant_id = #{cliantId}")
	ContactEntityForEdit findByCliantId(long cliantId);
	

}
