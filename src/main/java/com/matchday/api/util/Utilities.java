package com.matchday.api.util;

import java.util.ArrayList;
import java.util.List;

import com.matchday.api.model.dto.ResultDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

@Component
public class Utilities {

	/**
	 * Returns an object of type Pageable
	 * 
	 * @param page Zero-based page index, must NOT be negative
	 * @param size Number of elements on a page to be returned, it must be greater
	 *             than 0
	 * @param sort Array of sorts
	 * @return Pageable
	 */
	public Pageable buildPageable(int page, int size, String[] sort) {
		return PageRequest.of(page, size, Sort.by(buildOrders(sort)));
	}

	/**
	 * Retrieve an orders list
	 *
	 * @param sort Array of sorts
	 * @return List of orders
	 */
	public List<Order> buildOrders(String[] sort) {
		List<Order> orders = new ArrayList<>();

		if (sort[0].contains(",")) {
			// When sorting by more than one column
			for (String sortOrder : sort) {
				String[] sort2 = sortOrder.split(",");
				orders.add(new Order(getSortDirection(sort2[1]), sort2[0]));
			}
		} else {
			// When sorted by a column
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}

		return orders;
	}

	/**
	 * We convert "asc/desc" into "Sort.Direction.ASC/Sort.Direction.DES"
	 *
	 * @param String Direction of management
	 * @return Direction of management
	 */
	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	/**
	 * We build the search result for pageable listings
	 * 
	 * @param page  Page object
	 * @param items Results listing
	 * @return Search Result
	 */
	public <T> ResultDto buildResultSearch(Page<T> page, Object items) {
		return new ResultDto(//
				page.getTotalElements(), //
				page.getTotalPages(), //
				page.getNumber(), //
				page.isFirst(), //
				page.isLast(), //
				page.hasPrevious(), //
				page.hasNext(), //
				items);
	}

}
