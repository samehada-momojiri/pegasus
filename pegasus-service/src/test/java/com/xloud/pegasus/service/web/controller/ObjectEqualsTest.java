package com.xloud.pegasus.service.web.controller;

import java.util.Objects;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ObjectEqualsTest {

	@Test
	public void test001() {
		Set<DataHolder> set = Sets.newHashSet();
		set.add(new DataHolder("AAA", "111"));
		set.add(new DataHolder("AAA", "111"));
		set.add(new DataHolder("AAA", "111"));
		set.add(new DataHolder("BBB", null));
		set.add(new DataHolder("BBB", null));
		set.add(new DataHolder("BBB", null));
		set.add(new DataHolder(null, "222"));
		set.add(new DataHolder(null, "222"));
		set.add(new DataHolder(null, "222"));
		System.out.println(set.size());

		{
			DataHolder target = new DataHolder("AAA", "111");
			System.out.println(contains(set, target));
		}
		{
			DataHolder target = new DataHolder(null, "111");
			System.out.println(contains(set, target));
		}
		{
			DataHolder target = new DataHolder("AAA", null);
			System.out.println(contains(set, target));
		}
		{
			DataHolder target = new DataHolder("CCC", null);
			System.out.println(contains(set, target));
		}
		{
			DataHolder target = new DataHolder(null, "333");
			System.out.println(contains(set, target));
		}
	}

	private boolean contains(Set<DataHolder> dataHolderSet, DataHolder target) {
		for (DataHolder dataHolder : dataHolderSet) {
			if (Objects.equals(dataHolder, target)) {
				return true;
			}
		}
		return false;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	public static class DataHolder {
		private String param1;
		private String param2;

		@Override
		public int hashCode() {
			return Objects.hash(param1, param2);
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof DataHolder)) {
				return false;
			}
			DataHolder other = (DataHolder) obj;
			if (this.param1 != null && Objects.equals(this.param1, other.param1)) {
				return true;
			}
			if (this.param2 != null && Objects.equals(this.param2, other.param2)) {
				return true;
			}
			return false;
		}

	}
}
