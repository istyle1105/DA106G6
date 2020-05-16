package com.trip_message.model;

import java.util.List;

public interface Trip_messageDAO_interface {
	public void insert(Trip_messageVO trip_messageVO);
    public void update(Trip_messageVO trip_messageVO);
    public void delete(String t_msg_id);
    public Trip_messageVO findByPrimaryKey(String t_msg_id);
    public List<Trip_messageVO> getAll();
}
